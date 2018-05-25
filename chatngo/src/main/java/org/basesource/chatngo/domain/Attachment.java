package org.basesource.chatngo.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.basesource.chatngo.domain.User;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Message message;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date attached;

    @Lob
    @NotNull
    private byte[] data;

}
