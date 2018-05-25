package org.basesource.chatngo.repository;

import org.basesource.chatngo.domain.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

}
