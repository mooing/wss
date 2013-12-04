package com.mooing.wss.common.model;

/**
 * @version $id$
 *  所有具有名字的模型的基类
 */

public class NamedModel extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模型实例的名字
	 */
	protected String name;
	
	public NamedModel(){
		
	}
	
	public NamedModel(Integer id, String name){
		super(id);
		this.name = name;
	}
	
	/** java bean get setter **/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
