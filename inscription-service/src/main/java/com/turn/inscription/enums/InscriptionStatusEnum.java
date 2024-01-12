package com.turn.inscription.enums;


/**
 *  Returns the front-end micro-node status enumeration
 */
public enum InscriptionStatusEnum {
	ALL("all", 0),
	IN_PROGRESS("In-progress" ,1),
	COMPLETED("Completed",2);
	private String name;

	private Integer code;

	InscriptionStatusEnum(String name, Integer code) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public Integer getCode() {
		return code;
	}

	public static InscriptionStatusEnum getEnumByCodeValue(int code){
		InscriptionStatusEnum[] allEnums = values();
		for(InscriptionStatusEnum microNodeStatusEnum : allEnums){
			if(microNodeStatusEnum.getCode()==code)
				return microNodeStatusEnum;
		}
		return null;
	}

	public static InscriptionStatusEnum getEnumByName(String name){
		InscriptionStatusEnum[] allEnums = values();
		for(InscriptionStatusEnum microNodeStatusEnum : allEnums){
			if(microNodeStatusEnum.getName().equalsIgnoreCase(name))
				return microNodeStatusEnum;
		}
		return null;
	}
}
