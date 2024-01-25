package com.fisa.infra.role.domain.entity;


import com.fisa.infra.account.domain.entity.Account;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "accounts_roles")
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AccountRole {

    @EmbeddedId
    private Pk pk;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "account_id")
        private Long accountId;

        @Column(name = "role_id")
        private Long roleId;

    }
}
