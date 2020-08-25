package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Getter@Setter
public class InMediaDTO {

	//este nos permitira hacer la busqueda en la DB del objeto Media sin saber su ID
	//sera el frontend quien se encarge de generarlo *?^##!! el frame id.
	//Estrategia -> Vue mirara si ha pichado en editar frame (getframeid) o en crear new frame (count frames + 1)
	private String frameId;
	private String docType;
	private String username;
	private String text;
	
	public InMediaDTO(String frameId, String docType, String username, String text) {
		this.frameId = frameId;
		this.docType = docType;
		this.username = username;
		this.text = text;
	}
	
	
}

