package lab.sharp.module.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lab.sharp.core.entity.BaseEntity;

@Entity
@Table(name = "auth_User")
public class User extends BaseEntity<Long> {

    /**
     * serialVersionUID:序列号
     * @since JDK 1.5
     */
    private static final long serialVersionUID = 8728775138491827366L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public long Id;
    @Column(name = "sLoginName", nullable = true)
	public String sLoginName = "";
    @Column(name = "sLoginPassword", nullable = true)
	public String sLoginPassword = "";
    
	public Long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
		putUsedField("Id", id);
	}

	
	public String getsLoginName() {
		return sLoginName;
	}
	public void setsLoginName(String sLoginName) {
		this.sLoginName = sLoginName;
		putUsedField("sLoginName", sLoginName);
	}
	public String getsLoginPassword() {
		return sLoginPassword;
	}
	public void setsLoginPassword(String sLoginPassword) {
		this.sLoginPassword = sLoginPassword;
		putUsedField("sLoginPassword", sLoginPassword);
	}


}
