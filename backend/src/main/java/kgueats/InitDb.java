package kgueats;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.member.repository.StudentRepository;
import kgueats.domain.store.model.entity.BusinessHour;
import kgueats.domain.store.model.entity.CampusEnum;
import kgueats.domain.store.model.entity.Food;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.repository.BusinessHourRepository;
import kgueats.domain.store.repository.FoodRepository;
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

        private final StudentRepository studentRepository;
        private final StoreRepository storeRepository;
        private final FoodRepository foodRepository;
        private final BusinessHourRepository businessHourRepository;

        public void dbInit() {
            Student student1 = new Student(201610010L, "student1", "password1");
            Student student2 = new Student(202210010L, "student2", "password2");
            studentRepository.save(student1);
            studentRepository.save(student2);

            insertStore1();
            insertStore2();
        }

        private void insertStore1() {
            Store store = new Store(CampusEnum.SUWON, "감성코어", "감성코어_Location");

            Arrays.stream(new BusinessHour[] {
                    new BusinessHour(LocalDateTime.now(), LocalDateTime.now()),
            }).forEach(businessHour -> {
                store.appendBusinessHour(businessHour);
                businessHourRepository.save(businessHour);
            });

            Arrays.stream(new Food[] {
                    new Food("돈까스", 8000L),
                    new Food("카레라이스", 6000L),
                    new Food("오므라이스", 6000L),
                    new Food("육회비빔밥", 10000L)
            }).forEach(food -> {
                foodRepository.save(food);
                store.appendFood(food);
            });

            storeRepository.save(store);
        }

        private void insertStore2() {
            Store store = new Store(CampusEnum.SUWON, "경기드림타워", "경기드림타워_Location");

            Arrays.stream(new BusinessHour[] {
                    new BusinessHour(LocalDateTime.now(), LocalDateTime.now()),
                    new BusinessHour(LocalDateTime.now(), LocalDateTime.now())
            }).forEach(businessHour -> {
                businessHourRepository.save(businessHour);
                store.appendBusinessHour(businessHour);
            });

            Arrays.stream(new Food[] {
                    new Food("돈까스", 8000L),
                    new Food("카레라이스", 6000L),
                    new Food("오므라이스", 6000L),
                    new Food("육회비빔밥", 10000L),
                    new Food("돈까스", 8000L),
                    new Food("카레라이스", 6000L),
                    new Food("오므라이스", 6000L),
                    new Food("육회비빔밥", 10000L)
            }).forEach(food -> {
                foodRepository.save(food);
                store.appendFood(food);
            });

            storeRepository.save(store);
        }

    }

}