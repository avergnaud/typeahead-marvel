package com.catamania;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import marvel.api.CharactersAPI;
import marvel.api.request.CharactersRequest;

/**
 * appelé à l'init de la webapp
 */
public class Init implements ServletContextListener {

	public static List<marvel.model.Character> persos = new ArrayList<marvel.model.Character>();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		CharactersAPI api = CharactersAPI
				.configureKeys("ac627b5a9da2dd5127e9583595c671f9",
						"c2110625d1f04ad9cf37d57cd2e9e4e2bddc6fc1")
				.configureProxyHost("px-internet")
				.configureProxyPort("80")
				.limiteMarvel(10).init();

		CharactersRequest firstRequest = api.requestBuilder()
				.limite(2000)
				.build();

		new Thread(new Runnable() {
			@Override
			public void run() {
				firstRequest.alimente(persos);
			}
		}).start();
	}
}
