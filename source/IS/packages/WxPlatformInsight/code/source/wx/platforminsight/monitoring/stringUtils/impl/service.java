package wx.platforminsight.monitoring.stringUtils.impl;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void convertToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(convertToString)>> ---
		// @sigtype java 3.5
		// [i] object:0:required object
		// [o] field:0:required objectAsString
		IDataMap plMap = new IDataMap(pipeline);
		Object object = plMap.get("object");
		if (object == null){
			plMap.put("objectAsString", null);
		}
		else{
			plMap.put("objectAsString", object.toString());
		}
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void splitPercentageAndAbsoluteValue (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(splitPercentageAndAbsoluteValue)>> ---
		// @sigtype java 3.5
		// [i] field:0:required formattedValue
		// [o] field:0:required percentage
		// [o] field:0:required absolute
		// [o] field:0:required unit
		IDataMap plMap = new IDataMap(pipeline);
		String formattedValue = plMap.getAsString("formattedValue");
		Matcher parts = formatPattern.matcher(formattedValue);
		if (parts.matches()){
			plMap.put("percentage", parts.group(1));
			plMap.put("absolute", parts.group(2));
			plMap.put("unit", parts.group(3));
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static Pattern formatPattern = Pattern.compile("^(\\d{1,3}) ?% ?\\((\\d+) ?([^\\)]+)*\\)$");
	// --- <<IS-END-SHARED>> ---
}

