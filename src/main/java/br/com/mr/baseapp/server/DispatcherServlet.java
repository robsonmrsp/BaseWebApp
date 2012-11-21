
package br.com.mr.baseapp.server;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
@SuppressWarnings("serial")
public class DispatcherServlet extends RemoteServiceServlet{

	private static final Logger LOGER = Logger.getLogger(DispatcherServlet.class);
	BeanFactory applicationContext ;

	private Object getBean(HttpServletRequest request) {
		String serviceName = getServiceNameByRequest(request);
		Object beanService = getBeanService(serviceName);

		if (!(beanService instanceof RemoteService)) {
			throw new IllegalArgumentException("Spring bean is not a GWT RemoteService: " + serviceName + " (" + beanService + ")");
		}
		LOGER.debug("Bean for service " + serviceName + " is " + beanService);
		return beanService;
	}

	/**
	 * Parse the service name from the request URL.
	 *
	 * @param request
	 * @return bean name
	 **/
	protected String getServiceNameByRequest(HttpServletRequest request) {
		String url = request.getRequestURI();
		String service = url.substring(url.lastIndexOf("/") + 1);

		LOGER.debug("Service for URL " + url + " is " + service);
		return service;
	}


	/**
	 * Look up a spring bean with the specified name in the current web
	 * application context.
	 *
	 * @param name
	 * bean name
	 * @return the bean
	 **/
	protected Object getBeanService(String name) {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if (applicationContext == null) {
			throw new IllegalStateException("No Spring web application context found");
		}
		if (!applicationContext.containsBean(name)) {
			throw new IllegalArgumentException("Spring bean not found: " + name);
		}
		return applicationContext.getBean(name);
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		try {
			Object handler = getBean(getThreadLocalRequest());
			RPCRequest rpcRequest = RPC.decodeRequest(payload, handler.getClass(), this);
			onAfterRequestDeserialized(rpcRequest);

			LOGER.debug("Invoking " + handler.getClass().getName() + "." + rpcRequest.getMethod().getName());
			
			return RPC.invokeAndEncodeResponse(handler, rpcRequest.getMethod(), rpcRequest.getParameters(), rpcRequest.getSerializationPolicy());
		} catch (IncompatibleRemoteServiceException ex) {
			LOGER.error("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		} catch (RpcTokenException tokenException) {
			LOGER.error("An RpcTokenException was thrown while processing this call.",	tokenException);
			return RPC.encodeResponseForFailure(null, tokenException);
		}	
	}
}
