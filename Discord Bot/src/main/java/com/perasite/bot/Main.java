package com.perasite.bot;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.mashape.unirest.http.utils.Base64Coder;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageAttachment;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class Main {

	public static final String EMAIL = "pretocki3@naver.com";
	public static final String PW = "aG9vbnkxMjA2";

	public static final List<String> LISTENING_SERVER = Arrays.asList("개발자방", "봇 테스트 방");

	public static void main(String[] args) throws Exception {
		DiscordAPI api = Javacord.getApi(EMAIL, Base64Coder.decodeString(PW));
		api.connectBlocking();
		api.registerListener(new MessageCreateListener() {

			public void onMessageCreate(DiscordAPI api, Message msg) {
				long time = System.nanoTime();
				if (!LISTENING_SERVER.contains(msg.getChannelReceiver().getServer().getName()))
					return;

				if (msg.getAttachments().size() == 0) {
					switch (msg.getContent()) {
					case "핑":
						msg.edit("퐁! " + timeBenchmark(time));
						new Thread(() -> {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							msg.delete();
						}).start();
						break;
					case "히오스":
						msg.reply("", new EmbedBuilder().setImage("http://i.imgur.com/yjCvTa6.png"));
						break;
					case "테스트":
						msg.edit(
								"server: " + msg.getChannelReceiver().getServer().getName() +
										",name: " + msg.getAuthor().getName() +
										",content: " + msg.getContent());
						break;
					default:
						break;
					}
				} else {
					if (msg.getContent().trim().equalsIgnoreCase("[아스키]")) {
						List<MessageAttachment> attachments = new ArrayList<MessageAttachment>(msg.getAttachments());
						URL url = attachments.get(0).getUrl();
						BufferedImage image = getImageFromHTTPS(url);
						String text = ASCII.imageToText(image);
						msg.delete();
						msg.reply(text.substring(0, 1999));
					}
				}
			}
		});
	}

	public static String timeBenchmark(long pastTime) {
		long diff = System.nanoTime() - pastTime;
		return diff + "ns (" + (diff / 1000000) + "ms)";
	}

	public static BufferedImage getImageFromHTTPS(URL url) {
		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}
				}
		};

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			return ImageIO.read(connection.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BufferedImage(0, 0, 0);
	}
}
