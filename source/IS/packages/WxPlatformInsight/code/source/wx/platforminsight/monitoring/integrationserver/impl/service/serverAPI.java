package wx.platforminsight.monitoring.integrationserver.impl.service;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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

