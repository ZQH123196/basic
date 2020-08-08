#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <strings.h>
#include <string.h>
#include <signal.h>
#include <errno.h>
#include <stdbool.h>

#define SHM_FILE_NAME "./shm_file"
#define SHM_SIZE 4096


void print_err(char *estr) {
	perror(estr);	
	exit(-1);
}

pid_t get_shm(void) {
    pid_t fd = -1;
    int key = -1;

    pid_t shmid = -1;

    fd = open(SHM_FILE_NAME, O_CREAT | O_RDWR, 0664);
    if (fd == -1) print_err("open fail");

    key = ftok(SHM_FILE_NAME, 1);
    if (key == -1) print_err("ftok fail");
 
    shmid = shmget(key, SHM_SIZE, 0664|IPC_CREAT);
    if (shmid == -1) print_err("shmget fail");

    return shmid;

}

int main(void) {

    pid_t shmid = -1;
    
    shmid = get_shm();

    while (true)
    {
        
        sleep(1);
    }
    

    exit(0);
}


