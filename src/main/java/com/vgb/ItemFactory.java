package com.vgb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class ItemFactory {

	private static final Map<Class<?>, Function<String[], Item>> itemCreators = new HashMap<>();
	/*
	 * Lambda functions, and dynamically constructs Item Objects
	 * 
	*/
	
	public ItemFactory(Map<UUID, Company> companyLookup) {
		
		itemCreators.put(Equipment.class, parts -> {
			if(parts.length < 5) throw new IllegalArgumentException("Invalid Equipment entry: " + Arrays.toString(parts));
			return new Equipment(UUID.fromString(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]));
		});
		
		itemCreators.put(Material.class, parts -> {
			if(parts.length < 5) throw new IllegalArgumentException("InvalidMaterial object" + Arrays.toString(parts));
			return new Material(UUID.fromString(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]));
		});
		
		/*
		 * Uses companyLookup here to map companies using UUIDs 
		 */
		itemCreators.put(Contract.class, parts ->{
			
			UUID companyUuid = UUID.fromString(parts[3].trim());
            Company company = companyLookup.get(companyUuid);
            
            if (company == null) {
                throw new IllegalArgumentException("Company with UUID " + companyUuid + " not found.");
            }
			
			if(parts.length < 4) throw new IllegalArgumentException("Invalid Contractobject" + Arrays.toString(parts));
			return new Contract(UUID.fromString(parts[0]), parts[2], company);
		});
		
		
	}
	
	
	/*
	 * Creates an Item depending on the Class<?> type parsed in
	 * 
	 */
	public Item createItem(Class<?> itemType, String[] parts) {
        Function<String[], Item> creator = itemCreators.get(itemType);
        if (creator != null) {
            return creator.apply(parts);
        }
        throw new IllegalArgumentException("Unknown item type: " + itemType.getSimpleName());
    }
}
