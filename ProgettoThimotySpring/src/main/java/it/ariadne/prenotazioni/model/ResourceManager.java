package it.ariadne.prenotazioni.model;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import aj.org.objectweb.asm.Type;

public class ResourceManager implements JsonDeserializer<Risorsa> {
	
		private static final String RESOURCESPACKAGE = "it.ariadne.prenotazioni.model.resources.";

		@Override
		public Risorsa deserialize(JsonElement jsonElement, java.lang.reflect.Type type, JsonDeserializationContext context)
				throws com.google.gson.JsonParseException {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
	        JsonElement resourceType = jsonObject.get("risorsa_type");
	        if (type != null) {
//	           
	        	try {
					return context.deserialize(jsonObject, Class.forName(RESOURCESPACKAGE  + resourceType.getAsString()));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return null;
		}

		
		

		
	
}
