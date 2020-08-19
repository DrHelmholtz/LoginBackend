package com.main.login.helper;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Hash {

	/**
	 * SHA256 hashing function
	 * @param str before hash
	 * @return String after hash
	 */
	public static String toSHAHash(String str) {
		return Hashing.sha256()
				  .hashString(str, StandardCharsets.UTF_8)
				  .toString();
	}
}
