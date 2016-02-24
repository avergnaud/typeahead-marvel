package com.catamania.util;

import com.catamania.PersoBean;

public class Characters {
	public static PersoBean toPersoBean(marvel.model.Character c) {

		PersoBean p = new PersoBean();
		p.setNom(c.getName());
		p.setUrlPic(c.getThumbnail().getPath() + "/portrait_xlarge."
				+ c.getThumbnail().getExtension());
		return p;

	}
}
