package com.vgb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PersonFactory {

	
	public static Person createPerson(String[] parts) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CSV format, missing required fields.");
        }

        String uuidStr = parts[0].trim();
        if (uuidStr.isEmpty()) {
            throw new IllegalArgumentException("Missing UUID in CSV: " + Arrays.toString(parts));
        }
        
        UUID uuid;
        try {
            uuid = UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + uuidStr);
        }

        String firstName = parts[1].trim();
        String lastName = parts[2].trim();
        String phone = parts[3].trim();

        List<String> emails = new ArrayList<>();
        for (int i = 4; i < parts.length; i++) {
            if (!parts[i].trim().isEmpty()) {
                emails.add(parts[i].trim());
            }
        }

        return new Person(uuid, firstName, lastName, phone, emails);
    }
}
