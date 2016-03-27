package Converters;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentSet;

import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.Mapper;

public class HibernateCollectionConverter extends CollectionConverter {
        public HibernateCollectionConverter(Mapper mapper) {
                super(mapper);
        }

        public boolean canConvert(Class type) {
                return super.canConvert(type)
                                || type == PersistentList.class
                                || type == PersistentSet.class
                                || type == PersistentBag.class
                                ;
        }
}

