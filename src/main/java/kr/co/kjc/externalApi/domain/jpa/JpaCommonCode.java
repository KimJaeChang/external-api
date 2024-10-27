package kr.co.kjc.externalApi.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@Table(name = "COMMON_CODE")
@ToString
public class JpaCommonCode extends JpaBaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "parent_code")
  private String parentCode;

  @Column(name = "child_code")
  private String childCode;

  @Column(name = "code_name")
  private String codeName;

  @Column(name = "description")
  private String description;

  public static JpaCommonCode of(String parentCode, String childCode) {
    JpaCommonCode result = new JpaCommonCode();
    result.parentCode = parentCode;
    result.childCode = childCode;
    return result;
  }

  public static JpaCommonCode createExampleByParentCode(String parentCode) {
    JpaCommonCode result = new JpaCommonCode();
    result.parentCode = parentCode;
    return result;
  }

  public static JpaCommonCode createExampleByChildCode(String childCode) {
    JpaCommonCode result = new JpaCommonCode();
    result.childCode = childCode;
    return result;
  }
}
