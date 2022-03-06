package br.com.clone.spotify.controllers;

import br.com.clone.spotify.client.album.AlbumSearch;
import br.com.clone.spotify.client.authorization.ClientCredentials;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

@RestController
@RequestMapping("api/v1")
public class Api {

    @GetMapping(value = "")
    public AlbumSimplified[] run() {
        String accessToken = getAccesToken();
        return AlbumSearch.getAlbums(accessToken);
    }

    public String getAccesToken(){
        return ClientCredentials.clientCredentials_Sync();
    }




}
