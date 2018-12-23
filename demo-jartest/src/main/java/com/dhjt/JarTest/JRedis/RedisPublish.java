package com.dhjt.JarTest.JRedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.hutool.db.sql.Order;
import redis.clients.jedis.Jedis;

public class RedisPublish {
	public static void main(String[] args) throws IOException {
		System.out.println("发布者 ");
		Jedis jRedis = new Jedis("localhost");
		jRedis.publish("JRedisChat", "my name is chenLong");
//      jRedis.publish("JRedisChat1","Hello chenLong!");
		Order o = new Order();// 一个实体类..
		ByteArrayOutputStream byt = new ByteArrayOutputStream();
		ObjectOutputStream obj = new ObjectOutputStream(byt);

		obj.writeObject(o);

		byte[] bytes = byt.toByteArray();
		// 可写入byte和字符串.
		jRedis.publish("JRedisChat1".getBytes(), bytes);
	}

}
