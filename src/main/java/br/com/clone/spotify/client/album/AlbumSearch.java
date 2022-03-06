package br.com.clone.spotify.client.album;


import br.com.clone.spotify.dto.AlbumDTO;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import java.io.IOException;

public class AlbumSearch {

    public static AlbumSimplified[] getAlbums(AlbumDTO albumDTO, String accessToken) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
      SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(albumDTO.getName())
          .build();
        return searchAlbums_Sync(searchAlbumsRequest);

    }

    public static AlbumSimplified[] searchAlbums_Sync(SearchAlbumsRequest searchAlbumsRequest) {
        try {

            final Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
            return albumSimplifiedPaging.getItems();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

}
