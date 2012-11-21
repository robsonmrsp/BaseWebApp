package br.com.mr.baseapp.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MapImages extends ClientBundle {

	public static final MapImages INSTANCE = GWT.create(MapImages.class);

	@Source("br/com/mr/baseapp/client/images/icone_sinotico.png")
	ImageResource iconeSinotico64();

	@Source("br/com/mr/baseapp/client/images/icone_mapa.png")
	ImageResource iconeMapa64();
}