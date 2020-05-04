#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>s

/*
To perform communication using message queues, following are the steps −

Step 1 − Create a message queue or connect to an already existing message queue (msgget())
Step 2 − Write into message queue (msgsnd())
Step 3 − Read from the message queue (msgrcv())
Step 4 − Perform control operations on the message queue (msgctl())
*/


/*
* This system call creates or allocates a System V message queue. Following arguments need to be passed −
*     The first argument, key, recognizes the message queue. The key can be either an arbitrary value or one that can be derived from the library function ftok().
*     The second argument, shmflg, specifies the required message queue flag/s such as IPC_CREAT (creating message queue if not exists) or IPC_EXCL (Used with IPC_CREAT to create the message queue and the call fails, if the message queue already exists). Need to pass the permissions as well.
*/
int msgget(key_t key, int msgflg);





int main(void) {

    puts("eor");
    return 0;
}