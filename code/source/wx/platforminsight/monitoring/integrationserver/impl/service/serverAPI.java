package wx.platforminsight.monitoring.integrationserver.impl.service;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.wm.app.b2b.server.dispatcher.trigger.Trigger;
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.ns.Namespace;
import com.softwareag.util.IDataMap;
import com.wm.app.b2b.server.ServerAPI;
import com.softwareag.is.monitor.api.API;
import com.softwareag.is.monitor.api.FieldNames;
import com.softwareag.is.monitor.api.ISearchResult;
import com.softwareag.is.monitor.api.ISearchResults;
// --- <<IS-END-IMPORTS>> ---

public final class serverAPI

{
	// ---( internal utility methods )---

	final static serverAPI _instance = new serverAPI();

	static serverAPI _newInstance() { return new serverAPI(); }

	static serverAPI _cast(Object o) { return (serverAPI)o; }

	// ---( server methods )---




	public static final void getPackageNameOfService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPackageNameOfService)>> ---
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
			NSService  service = (NSService) Namespace.current().getNode( nsName );
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



	public static final void getServiceRuntimeMetrics (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getServiceRuntimeMetrics)>> ---
		// @sigtype java 3.5
		// [i] field:1:optional exclude
		// [o] record:1:required metrics
		// [o] - field:0:required metric_name
		// [o] - object:0:required value
		// [o] - recref:1:optional labels wx.prometheus.pub.documents:label
		IDataCursor pipelineCursor = pipeline.getCursor();
		String[]	        filter = IDataUtil.getStringArray( pipelineCursor, "exclude" );
		pipelineCursor.destroy();
		
		try {
			ISearchResults searchResults = API.get().getPrometheusMetrics();
			if ( searchResults == null || ISearchResults.SUCCESS_CODE != searchResults.getStatus() )
				return;
			
			Iterator<ISearchResult> iter = searchResults.iterator();
		
			List<IData>          metrics = new ArrayList<IData>();
			int                        i = 0;
			while ( iter.hasNext() ) {
				ISearchResult         sr = iter.next();
				String         fieldName = sr.getStringValue( FieldNames.KEY_FIELD );
				String        metricName = sr.getStringValue( FieldNames.METRIC_NAME_FIELD );
				String       serviceName = sr.getStringValue( "nsName" );
				String             value = sr.getStringValue( fieldName );
		
				if ( "U".equals( sr.getStringValue( FieldNames.EXEC_STATUS_FIELD ) ) ) {
					// skip in-flight data
					continue;                     
				} 
		
				if ( serviceName != null && ! contains( filter, serviceName ) ) {
					//System.out.println( "Monitor KPI: " + fieldName + " Metric: " + metricName + " Label: " + label + "  Value: " + value);       
					IData id = IDataFactory.create();
					IDataCursor metricsCursor = id.getCursor();
					IDataUtil.put( metricsCursor, "metric_name", metricName );
					IDataUtil.put( metricsCursor, "value", Float.valueOf( value ) );
					try {
						NSService  service = (NSService) Namespace.current().getNode( serviceName );
						IDataUtil.put( metricsCursor, "labels", createLabels( serviceName, service.getPackage().getName() ) );
						metricsCursor.destroy();
						metrics.add( id );
						i++;
					}
					catch ( ClassCastException e ) {
						metricsCursor.destroy();						
					}
				}
			}
			pipelineCursor = pipeline.getCursor();
			IDataUtil.put( pipelineCursor, "metrics", metrics.toArray(new IData[metrics.size() ]) );
			pipelineCursor.destroy();
		}
		catch ( Throwable e ) {
			throw new ServiceException( e );
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static boolean contains( String[] filter, String name ) {
		if ( filter != null )
			for ( int i = 0; i < filter.length; i++ )
				if ( name.startsWith( filter[ i ] ) )
					return true;
		return false;
	}
	
	
	private static IData[] createLabels( String service, String packageName ) {
		IData back[] = new IData[ 2 ];
		back[ 0 ] = IDataFactory.create();
		back[ 1 ] = IDataFactory.create();
		IDataCursor cursor = back[ 0 ].getCursor();
		IDataUtil.put( cursor, "label_name", "name" );
		IDataUtil.put( cursor, "label_value", service );
		cursor.destroy();
		cursor = back[ 1 ].getCursor();
		IDataUtil.put( cursor, "label_name", "package" );
		IDataUtil.put( cursor, "label_value", packageName );
		cursor.destroy();
	
		return back;
	}
		
	// --- <<IS-END-SHARED>> ---
}

