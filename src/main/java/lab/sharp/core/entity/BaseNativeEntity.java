/**
 * Copyright (c) 2012
 */
package lab.sharp.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 框架提供一个基础的Native方式的实体对象定义参考
 * 具体可根据项目考虑选用其他主键如自增、序列等方式，只需修改相关泛型参数类型和主键定义注解即可
 * 各属性定义可先简单定义MetaData注解即可，具体细节的控制属性含义可查看具体代码注释说明
 * 创建时间: 2016年8月1日 下午5:26:05
 * 创建人： 邢凌霄
 * 版本： 1.0
 */
public abstract class BaseNativeEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 693468696296687126L;

	
    /**获取ID的抽象方法，在子类中实现，DAO的通用操作中使用，没有ID的数据库表不使用*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public long Id;
	public Long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
		putUsedField("Id", id);
	}
	
}
