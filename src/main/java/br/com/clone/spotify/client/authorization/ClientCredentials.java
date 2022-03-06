package br.com.clone.spotify.client.authorization;

import br.com.clone.spotify.constants.Constants;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

@Service
public class ClientCredentials {

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(Constants.CLIENT_ID)
            .setClientSecret(Constants.CLIENT_SECRET)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    public static String clientCredentials_Sync() {
        try {
            final se.michaelthelin.spotify.model_objects.credentials.ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            return clientCredentials.getAccessToken();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

}
