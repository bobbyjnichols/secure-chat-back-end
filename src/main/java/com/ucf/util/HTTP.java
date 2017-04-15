package com.ucf.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTP extends OkHttpClient {

  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  OkHttpClient client = new OkHttpClient();

  private HttpUrl endpoint;
  private JSONObject headers;
  private JSONObject body;
  private Response response;

  public HTTP(HttpUrl endpoint) {
    super();

    this.endpoint = endpoint;
    this.headers = new JSONObject();
    this.body = new JSONObject();
  }

  public HTTP(String endpoint) {
    super();

    this.endpoint = HttpUrl.parse(endpoint);
    this.headers = new JSONObject();
    this.body = new JSONObject();
  }

  public HTTP GET() throws IOException {

    okhttp3.Request.Builder request = new Request.Builder()
      .url(this.endpoint)
      .get();

    Iterator<String> keys = headers.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      request.addHeader(key, (String) headers.get(key));
    }

    this.response = this.newCall(request.build()).execute();

    return this;
  }

  public HTTP DELETE() throws IOException {

    okhttp3.Request.Builder request = new Request.Builder()
      .url(this.endpoint)
      .delete();

    Iterator<String> keys = headers.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      request.addHeader(key, (String) headers.get(key));
    }

    this.response = this.newCall(request.build()).execute();

    return this;
  }

  public HTTP POST() throws Exception {
    return this.POST(false);
  }

  public HTTP POST(Boolean useRawJSON) throws IOException {

    okhttp3.Request.Builder request = new Request.Builder()
      .url(this.endpoint)
      .post((useRawJSON) ? this.getBodyAsJSON() : this.getBodyAsForm());

    Iterator<String> keys = headers.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      request.addHeader(key, (String) headers.get(key));
    }

    this.response = this.newCall(request.build()).execute();

    return this;
  }

  public HTTP PUT() throws Exception {
    return this.PUT(false);
  }

  public HTTP PUT(Boolean useRawJSON) throws IOException {

    okhttp3.Request.Builder request = new Request.Builder()
      .url(this.endpoint)
      .put((useRawJSON) ? this.getBodyAsJSON() : this.getBodyAsForm());

    Iterator<String> keys = headers.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      request.addHeader(key, (String) headers.get(key));
    }

    this.response = this.newCall(request.build()).execute();

    return this;
  }

  public HTTP CLOSE() {
    this.response.close();

    return this;
  }

  public HTTP addHeader(String key, String value) {
    headers.put(key, value);
    return this;
  }

  public HTTP addBody(String key, Object value) {
    body.put(key, value);
    return this;
  }

  private RequestBody getBodyAsForm() {

    okhttp3.FormBody.Builder bodyBuilder = new FormBody.Builder();

    Iterator<String> keys = body.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      bodyBuilder.add(key, (String) body.get(key));
    }

    return bodyBuilder.build();
  }

  private RequestBody getBodyAsJSON() {
    return RequestBody.create(JSON, body.toString());
  }

  public JSONObject getResponseBody() throws JSONException, IOException {

    JSONObject body = new JSONObject()
      .put("body", new JSONObject(this.response.body().string()))
      .put("code", this.response.code());

    JSONObject networkResponse = new JSONObject(this.response.networkResponse());

    Iterator<String> keys = networkResponse.keys();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      body.put(key, networkResponse.get(key));
    }

    return body;
  }

  public JSONObject getHeaders() {

    Map<?, ?> pairs = response.headers().toMultimap();
    JSONObject headers = new JSONObject();
    Iterator<?> keys = pairs.keySet().iterator();

    while (keys.hasNext()) {
      String key = (String) keys.next();
      headers.put(key, pairs.get(key));
    }

    return headers;
  }

  public JSONObject Response() {

    JSONObject resp = new JSONObject();

    if (this.response != null) {
      try {
        resp
          .put("response", this.getResponseBody())
          .put("headers", this.getHeaders());
      } catch (JSONException | IOException e) {
        e.printStackTrace();
      }
    } else {
      resp
        .put("endpoint", endpoint)
        .put("body", this.body);
    }

    return resp;
  }

  @Override
  public String toString() {

    return this.Response().toString(2);
  }
}
