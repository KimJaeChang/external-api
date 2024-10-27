package kr.co.kjc.externalApi.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MEMBER")
public class JpaMember extends JpaBaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "id")
//  @SequenceGenerator(name = "PAYMENT_SEQ", schema = "CHASYGO", sequenceName = "PAYMENT_SEQ", allocationSize = 1, initialValue = 100)
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private JpaCompany company;

  @Column(name = "member_userid")
  private String userId;

  @Column(name = "member_uuid")
  private String uuid;

  @Column(name = "member_username")
  private String userName;

  @Column(name = "member_handphone")
  private String handPhone;

  public static JpaMember of(String userId, String userName, String handPhone, JpaCompany company) {
    JpaMember result = new JpaMember();
    result.company = company;
    result.userId = userId;
    result.uuid = UUID.randomUUID().toString();
    result.userName = userName;
    result.handPhone = handPhone;
    return result;
  }

  public static JpaMember createExampleByUuid(String uuid) {
    JpaMember result = new JpaMember();
    result.uuid = uuid;
    return result;
  }
}
