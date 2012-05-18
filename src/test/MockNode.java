package test;

/**
* Created by IntelliJ IDEA.
* User: monmohas
* Date: 28/4/12
* Time: 11:59 AM
* To change this template use File | Settings | File Templates.
*/
class MockNode {
    String name;

    MockNode(String name) {
        this.name = name;
    }

    public int getDegree() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MockNode person = (MockNode) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
