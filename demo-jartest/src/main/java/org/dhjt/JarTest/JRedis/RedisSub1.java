package org.dhjt.JarTest.JRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSub1 {
	public static void main(String[] args) {
		System.out.println("订阅者..1");
		final Jedis jRedis = new Jedis("localhost");

		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				// 执行订阅消息
				super.onMessage(channel, message);
				// 终止订阅
				super.unsubscribe();
				System.out.println(message);
			}
		};
		jRedis.subscribe(jedisPubSub, "JRedisChat1");
	}

}
