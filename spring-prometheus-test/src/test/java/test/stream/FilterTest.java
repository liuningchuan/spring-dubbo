package test.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FilterTest {

    @Test
    public void filterTest() {

        List<Person> persons = Arrays.asList(
                new Person("1","LiuNing", 25),
                new Person("2","LiuNing", 28),
                new Person("3","Eason", 25),
                new Person("4","Eason", 28),
                new Person("5","Jack", 5)
        );

        //根据age属性去重排序
        List<Person> unique1 = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))), ArrayList::new)
        );
        System.out.println(unique1);

        //根据id+name属性去重排序
        List<Person> unique2 = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(person ->
                                person.getId() + ":" + person.getName()))), ArrayList::new)
        );
        System.out.println(unique2);

        //对persons里的对象进行去重排序
        //在TreeSet的构造器中传入一个实现Comparator对象的比较器，返回1则是从小到大排序，反而之从大到小排序
        Set<Person> set = new TreeSet<>((person1, person2) -> {
            int x = person1.getAge();
            int y = person2.getAge();
            return Integer.compare(x, y);
        });
        set.addAll(persons);
        System.out.println(new ArrayList<>(set));

        //list过滤
        List<Person> filterList = persons.stream().filter(p ->
                p.getId().equals("1")).collect(Collectors.toList());
        System.out.println(filterList);

        //List转Map
        Map<String, String> collect = persons.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(collect);

        //提取出list对象中的一个属性
        List<String> stIdList1 = persons.stream().map(Person::getId).collect(Collectors.toList());
        System.out.println(stIdList1);

        //提取出list对象中的一个属性并去重
        List<String> stIdList2 = persons.stream().map(Person::getId).distinct().collect(Collectors.toList());
        System.out.println(stIdList2);

    }

    public static class Person {

        private String id;

        private String name;

        private Integer age;

        public Person(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
