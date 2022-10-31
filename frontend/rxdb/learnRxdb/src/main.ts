import { createRxDatabase } from 'rxdb';
/**
 * For browsers, we use the dexie.js based storage
 * which stores data in IndexedDB.
 * In other JavaScript runtimes, we can use different storages.
 * @link https://rxdb.info/rx-storage.html
 */
import { getRxStorageDexie } from 'rxdb/plugins/dexie';


// create a database
const myDatabase = await createRxDatabase({
    name: 'heroesdb', // the name of the database
    storage: getRxStorageDexie()
});

// Create a schema for a collection
const mySchema = {
    title: 'human schema',
    version: 0,
    primaryKey: 'passportId',
    type: 'object',
    properties: {
        passportId: {
            type: 'string',
            maxLength: 100 // <- the primary key must have set maxLength
        },
        firstName: {
            type: 'string'
        },
        lastName: {
            type: 'string'
        },
        age: {
            description: 'age in years',
            type: 'integer',

            // number fields that are used in an index, must have set minium, maximum and multipleOf
            minimum: 0,
            maximum: 150,
            multipleOf: 1
        }
    },
    required: ['firstName', 'lastName', 'passportId'],
    indexes: ['age']
}

// add collections
const myCollections = await myDatabase.addCollections({
    humans: {
        schema: mySchema
    },
});


// insert a document
const myDocument = await myDatabase.humans.insert({
    passportId: 'foobar',
    firstName: 'Alice',
    lastName: 'Bobby',
    age: 42
});


myDocument.lastName$.subscribe((lastName: any) => {
    console.log('lastName is now ' + lastName);
});

const foundDocuments = await myDatabase.humans.find({
    selector: {
        age: {
            $gt: 21
        }
    }
}).exec();