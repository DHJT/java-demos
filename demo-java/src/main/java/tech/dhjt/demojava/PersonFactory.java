package tech.dhjt.demojava;

import tech.dhjt.demojava.bean.Person;

public interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}
