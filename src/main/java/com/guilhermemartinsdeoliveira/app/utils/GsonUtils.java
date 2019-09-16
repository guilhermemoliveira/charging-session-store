package com.guilhermemartinsdeoliveira.app.utils;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GsonUtils {
	
	private GsonUtils() {
		
	}

	private static Gson gson = new GsonBuilder()
//			.setPrettyPrinting()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe()).create();

	private static final class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
		@Override
		public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime) throws IOException {
			jsonWriter.value(localDateTime.toString());
		}

		@Override
		public LocalDateTime read(final JsonReader jsonReader) throws IOException {
			return LocalDateTime.parse(jsonReader.nextString());
		}
	}

	public static Gson getGson() {
		return gson;
	}

}