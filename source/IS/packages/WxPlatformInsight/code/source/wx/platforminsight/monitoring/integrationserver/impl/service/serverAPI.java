package wx.platforminsight.monitoring.integrationserver.impl.service;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.dispatcher.trigger.Trigger;
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.ns.Namespace;
import com.softwareag.util.IDataMap;
import com.wm.app.b2b.server.ServerAPI;
// --- <<IS-END-IMPORTS>> ---

public final class serverAPI

{
	// ---( internal utility methods )---

	final static serverAPI _instance = new serverAPI();

	static serverAPI _newInstance() { return new serverAPI(); }

	static serverAPI _cast(Object o) { return (serverAPI)o; }

	// ---( server methods )---




	public static final void getPackageNameOfTrigger (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPackageNameOfTrigger)>> ---
		// @sigtype java 3.5
		// [i] field:0:required nsName
		// [i] field:0:optional default
		// [o] field:0:required packageName
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	nsName    = IDataUtil.getString( pipelineCursor, "nsName" );
		String  defaultPN = IDataUtil.getString( pipelineCursor, "default" );
		pipelineCursor.destroy();
		
		if ( defaultPN == null ) 
			defaultPN = "unknown";
		String       back = defaultPN;
		
		if ( nsName != null && nsName.length() > 0 ) {
			try {
			Trigger    service = (Trigger) Namespace.current().getNode( nsName );
			back               = service.getPackage().getName();
			}
			catch (Throwable e) {
				//Suppress exception
			}
		}
		pipelineCursor = pipeline.getCursor();
		IDataUtil.put(pipelineCursor, "packageName", back );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getServerName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getServerName)>> ---
		// @sigtype java 3.5
		// [o] field:0:required serverName
		String serverName = ServerAPI.getServerName();
		new IDataMap(pipeline).put("serverName", serverName);
			
		// --- <<IS-END>> ---

                
	}
}

