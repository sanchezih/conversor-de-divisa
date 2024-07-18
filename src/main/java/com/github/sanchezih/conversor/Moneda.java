package com.github.sanchezih.conversor;

import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Moneda {

//	public static String obtenerCodigo(String opcion) {
//		return switch (opcion) {
//		case "0" -> "";
//		case "1" -> "ARS";
//		case "2" -> "BOB";
//		case "3" -> "BRL";
//		case "4" -> "CLP";
//		case "5" -> "COP";
//		case "6" -> "USD";
//		case "7" -> "MXN";
//		default -> null;
//		};
//	}

	/**
	 * 
	 * @param opcion
	 * @return
	 */
	public static String obtenerCodigo(String opcion) {
		String codigo;

		switch (opcion) {
		case "0":
			codigo = "";
			break;
		case "1":
			codigo = "ARS";
			break;
		case "2":
			codigo = "BOB";
			break;
		case "3":
			codigo = "BRL";
			break;
		case "4":
			codigo = "CLP";
			break;
		case "5":
			codigo = "COP";
			break;
		case "6":
			codigo = "USD";
			break;
		case "7":
			codigo = "MXN";
			break;
		default:
			codigo = null;
		}
		
		return codigo;
	}

	/**
	 * 
	 * @param origen
	 * @param destino
	 * @return
	 */
	public static double obtenerTaza(String origen, String destino) {
		
		URI direccion = URI.create("https://open.er-api.com/v6/latest/" + origen);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			var json = JsonParser.parseString(response.body()).getAsJsonObject();
			if (json.get("result").getAsString().contains("success")) {
				return json.get("rates").getAsJsonObject().get(destino).getAsDouble();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return 0;
	}
}