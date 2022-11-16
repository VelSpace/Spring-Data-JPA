package ai.rezo.mshr.ManiHRBOOT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ai.rezo.mshr.ManiHRBOOT.Models.MasterData;

public interface MasterRepository extends JpaRepository<MasterData,Integer> {

	MasterData findBymobilephone(String mobile_phone);
}
