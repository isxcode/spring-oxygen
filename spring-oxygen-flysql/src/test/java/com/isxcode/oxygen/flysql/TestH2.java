package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.common.OxygenHolder;
import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.entity.FlysqlPage;
import com.isxcode.oxygen.flysql.enums.OrderType;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@JdbcTest
@ContextConfiguration(
		classes = {EnableEncryptablePropertiesConfiguration.class, FlysqlAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
public class TestH2 {

	private final Flysql flysql;

	public TestH2(@Qualifier("flysql") Flysql flysql) {

		this.flysql = flysql;
	}

	@Test
	public void testFlysql() {

		Dog dog1 = null;
		Dog dog2 = null;
		Dog dog3 = null;

		ArrayList<Dog> dogList = new ArrayList<>();
		try {
			dog1 =
					new Dog(
							1,
							"jack",
							1.1,
							new BigDecimal("1.1"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
			dog2 =
					new Dog(
							2,
							"john",
							1.2,
							new BigDecimal("1.2"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
			dog3 =
					new Dog(
							3,
							"rose",
							1.3,
							new BigDecimal("1.3"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
		} catch (NumberFormatException ignored) {
		}

		dogList.add(dog2);
		dogList.add(dog3);

		flysql.build().insert(Dog.class).save(dog1);
		flysql.build().insert(Dog.class).batchSave(dogList);

		System.out.println(
				"=========================== show all data   ====================================");
		List<Dog> dogQuery = flysql.build().select(Dog.class).query();
		dogQuery.forEach(System.out::println);

		System.out.println(
				"=========================== show page data   ====================================");
		FlysqlPage<Dog> dogQueryPage = flysql.build().select(Dog.class).queryPage(1, 2);
		System.out.println(dogQueryPage);

		System.out.println(
				"============================ show single data ===================================");
		Dog dogGetOne = flysql.build().select(Dog.class).eq("name", "rose").getOne();
		System.out.println(dogGetOne);

		System.out.println(
				"============================= show after delete data =================================");
		flysql.build().delete(Dog.class).eq("name", "john").doDelete();
		dogQuery = flysql.build().select(Dog.class).query();
		dogQuery.forEach(System.out::println);

		System.out.println(
				"============================== show after update data  =================================");
		flysql.build().update(Dog.class).eq("name", "jack").update("amountDouble", 9.9).doUpdate();
		dogQuery = flysql.build().select(Dog.class).query();
		dogQuery.forEach(System.out::println);

		System.out.println(
				"============================== doIsDelete()  =================================");
		flysql.build().update(Dog.class).eq("name", "jack").doIsDelete();
		dogQuery = flysql.build().select(Dog.class).query();
		dogQuery.forEach(System.out::println);

		System.out.println("============================== count()  =================================");
		Integer count =
				flysql
						.build()
						.update(Dog.class)
						.eq("name", "jack")
						.isNotDeleted()
						.isNull("id")
						.isToday("birthLocalDate")
						.notLike("name", "jack")
						.notIn("name", "jack")
						.ltEq("amountBigDecimal", 10)
						.gtEq("amountBigDecimal", 0)
						.notBetween("amountBigDecimal", 0, 10)
						.count();
		System.out.println(count);
	}

	@Test
	public void testAll() {

		Dog dog1 = null;
		Dog dog2 = null;
		Dog dog3 = null;

		ArrayList<Dog> dogList = new ArrayList<>();
		try {
			dog1 =
					new Dog(
							1,
							"jack",
							1.1,
							new BigDecimal("1.1"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
			dog2 =
					new Dog(
							2,
							"john",
							1.2,
							new BigDecimal("1.2"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
			dog3 =
					new Dog(
							3,
							"rose",
							1.3,
							new BigDecimal("1.3"),
							new Date(),
							LocalDate.now(),
							LocalDateTime.now(),
							true);
		} catch (NumberFormatException ignored) {
		}

		dogList.add(dog2);
		dogList.add(dog3);

		flysql.build().insert(Dog.class).save(dog1);
		flysql.build().insert(Dog.class).batchSave(dogList);

		System.out.println("============================== all()  =================================");
		List<Dog> dogQuery =
				flysql
						.build()
						.select(Dog.class)
						.isNotNull("id")
						.andStart()
						.eq("name", "jack")
						.andEnd()
						.isNotToday("birthLocalDate")
						.isDeleted()
						.like("name", "jack")
						.limit(1)
						.between("amountBigDecimal", 0, 10)
						.in("name", "jack")
						.gt("amountBigDecimal", 0)
						.and()
						.ne("name", "john")
						.or()
						.lt("amountBigDecimal", 10)
						.orderBy("birthLocalDate", OrderType.DESC)
						.query();
		dogQuery.forEach(System.out::println);

		OxygenHolder.setUserUuid("userId");
	}
}
