package wx.platforminsight.monitoring.processengine.impl;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.text.SimpleDateFormat;
import java.util.Locale;
// --- <<IS-END-IMPORTS>> ---

public final class map

{
	// ---( internal utility methods )---

	final static map _instance = new map();

	static map _newInstance() { return new map(); }

	static map _cast(Object o) { return (map)o; }

	// ---( server methods )---




	public static final void getDuration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getDuration)>> ---
		// @sigtype java 3.5
		// [i] object:0:required currentMillis
		// [i] field:0:required timestamp
		// [o] object:0:required duration
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		Object	currentMillis = IDataUtil.get( pipelineCursor, "currentMillis" );
		String	timestamp = IDataUtil.getString( pipelineCursor, "timestamp" );
		pipelineCursor.destroy();
		
		long back = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS" );
			long l = 0;
			long c = 0;
			if ( currentMillis != null ) {
				if ( currentMillis.getClass() == Integer.class )
					c = ( (Integer)  currentMillis ).longValue();
				if ( currentMillis.getClass() == Long.class )
					c = ( (Long)  currentMillis ).longValue();
			}
		
			l = formatter.parse( timestamp ).getTime();
			back = c - l;
		}
		catch ( Throwable e ) {
			throw new ServiceException( e );
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		Object duration = Long.valueOf( back );
		IDataUtil.put( pipelineCursor_1, "duration", duration );
		pipelineCursor_1.destroy();			
		// --- <<IS-END>> ---

                
	}
}

