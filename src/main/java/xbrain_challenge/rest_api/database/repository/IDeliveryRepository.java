package xbrain_challenge.rest_api.database.repository;

import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

}
