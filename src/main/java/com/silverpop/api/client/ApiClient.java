package com.silverpop.api.client;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ApiClient<REQUEST extends ApiRequest> {

    private Log log = LogFactory.getLog(this.getClass());

	private ApiCommandProcessor<REQUEST> commandProcessor;
	protected HttpClient httpClient;
    protected ApiSession session;


    public ApiSession getSession() {
   		return session;
   	}


	protected ApiClient(ApiCommandProcessor<REQUEST> commandProcessor, ApiSession session) {
		this(commandProcessor, new HttpClient(), session);
	}

	protected ApiClient(ApiCommandProcessor<REQUEST> commandProcessor, HttpClient httpClient, ApiSession session) {
		this.commandProcessor = commandProcessor;
		this.httpClient = httpClient;
		this.session = session;
	}

	public ApiResult executeCommand(ApiCommand command) throws ApiResultException {
		try {
			return validateSessionAndExecuteCommand(command);
		} catch(ApiResultException e) {
			if(retryCommand(e.getErrorResult())) {
				getSession().close();
				return validateSessionAndExecuteCommand(command);
			} else {
				throw e;
			}
		}		
	}

	private boolean retryCommand(ApiErrorResult errorResult) {
		return errorResult.isSessionInvalidOrExpired() && getSession().isReAuthenticate();
	}

	private ApiResult validateSessionAndExecuteCommand(ApiCommand command) throws ApiResultException {
		ensureSessionIsOpen();

		REQUEST request = commandProcessor.prepareRequest(command, getSession());
		HttpMethodBase method = commandProcessor.prepareMethod(getSession().getUrl(), request);

		String in = executeMethod(method);

		ApiResponse response = commandProcessor.processResponse(in, request.getResultType());
		
		return extractResult(command.getClass().getName(), response);
	}

	private void ensureSessionIsOpen() {
		if(!getSession().isOpen()) {
			getSession().open();
		}
	}

	protected String executeMethod(HttpMethodBase method) {
		try {
            log.error("executing method:" + method);
			httpClient.executeMethod(method);

			String responseBody = method.getResponseBodyAsString();
			if (!responseBody.isEmpty()) {
				return responseBody;
			} else {
				StringBuffer buffer = new StringBuffer();
				buffer.append("No response body was returned!\nResponse Headers-\n");
				Header[] headers = method.getResponseHeaders();
				for (Header header : headers) {
					buffer.append(header.getName() + ": " + header.getValue()+"\n");
				}
				buffer.append("Content length reported as: " + method.getResponseContentLength());
				throw new ApiException("Error executing API: " + buffer.toString());
			}
		} catch(Exception e) {
			throw new ApiException("Error executing API: ", e);
		}
	}

	private ApiResult extractResult(String requestName, ApiResponse response) throws ApiResultException {
		if(response.isSuccessful()) {
			return response.buildResult();
		} else {
            log.debug("Got Error Response");
			String msg = String.format("API call '%s' unsuccessful.", requestName);
			throw new ApiResultException(msg, response.buildErrorResult());
		}
	}
}
