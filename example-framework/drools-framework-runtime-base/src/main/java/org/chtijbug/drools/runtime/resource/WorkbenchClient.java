package org.chtijbug.drools.runtime.resource;

import com.google.common.base.Throwables;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.http.client.utils.HttpClientUtils.closeQuietly;

public class WorkbenchClient implements Closeable {
    private final CloseableHttpClient httpClient;
    private final String workbenchUrl;
    private CloseableHttpResponse response;
    private String username;
    private String password;

    public WorkbenchClient(String workbenchUrl, String username, String password) {
        this.workbenchUrl = workbenchUrl;
        this.username = username;
        this.password = password;
        try {
            URI submittedURI = new URI(workbenchUrl);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(submittedURI.getHost(), submittedURI.getPort()),
                    new UsernamePasswordCredentials(username, password)
            );
            this.httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        } catch (URISyntaxException e) {
            throw Throwables.propagate(e);
        }
    }

    public InputStream getWorkbenchResource(KnowledgeModule knowledgeModule) {
        // Build up url
        String url = workbenchUrl + "maven2/" + knowledgeModule.getGroupId().replaceAll("\\.", "/") + "/" + knowledgeModule.getArtifactId() + "/" + knowledgeModule.getVersion() + "/" + knowledgeModule.getArtifactId() + "-" + knowledgeModule.getVersion() + ".jar";
        HttpGet httpget = new HttpGet(url);
        this.response = null;
        try {
            response = httpClient.execute(httpget);
            return response.getEntity().getContent();
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void close() {
        closeQuietly(this.response);
        closeQuietly(this.httpClient);
    }

    public String getWorkbenchUrl() {
        return workbenchUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
