package kgueats;

import java.time.LocalTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.entity.BusinessHour;
import kgueats.domain.store.model.entity.CampusEnum;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.repository.BusinessHourRepository;
import kgueats.domain.store.repository.MenuRepository;
import kgueats.domain.store.repository.StoreRepository;

@Component
@RequiredArgsConstructor
public class InitDb {

	private final InitService initService;

	@PostConstruct
	public void init() {
		initService.dbInit();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {

		private final StoreRepository storeRepository;
		private final MenuRepository menuRepository;
		private final BusinessHourRepository businessHourRepository;

		public void dbInit() {
			insertStore1();
			insertStore2();
		}

		private void insertStore1() {
			Store store = new Store(CampusEnum.SUWON, "감성코어", "감성코어_Location");

			Arrays.stream(new BusinessHour[] {
				createBusinessHour(LocalTime.parse("11:30"), LocalTime.parse("13:30"))
			}).forEach(businessHour -> {
				store.appendBusinessHour(businessHour);
				businessHourRepository.save(businessHour);
			});

			Arrays.stream(new Menu[] {
				createMenu("돈까스", 8000L),
				createMenu("카레라이스", 6000L),
				createMenu("오므라이스", 6000L),
				createMenu("육회비빔밥", 10000L)
			}).forEach(menu -> {
				menuRepository.save(menu);
				store.appendMenu(menu);
			});

			storeRepository.save(store);
		}

		private void insertStore2() {
			Store store = new Store(CampusEnum.SUWON, "경기드림타워", "경기드림타워_Location");

			Arrays.stream(new BusinessHour[] {
				createBusinessHour(LocalTime.parse("11:30"), LocalTime.parse("13:30")),
				createBusinessHour(LocalTime.parse("17:30"), LocalTime.parse("19:00"))
			}).forEach(businessHour -> {
				businessHourRepository.save(businessHour);
				store.appendBusinessHour(businessHour);
			});

			Arrays.stream(new Menu[] {
				createMenu("돈까스", 8000L),
				createMenu("카레라이스", 6000L),
				createMenu("오므라이스", 6000L),
				createMenu("육회비빔밥", 10000L),
				createMenu("돈까스", 8000L),
				createMenu("카레라이스", 6000L),
				createMenu("오므라이스", 6000L),
				createMenu("육회비빔밥", 10000L)
			}).forEach(menu -> {
				menuRepository.save(menu);
				store.appendMenu(menu);
			});

			storeRepository.save(store);
		}

		private BusinessHour createBusinessHour(LocalTime openTime, LocalTime closeTime) {
			return new BusinessHour(openTime, closeTime);
		}

		private Menu createMenu(String name, Long price) {
			return new Menu(name, price);
		}

	}

}
