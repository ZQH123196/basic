class ThreadEncWorker {
    size: Number;
    workers: any[];

    constructor(url: URL, size: Number) {
        new Worker(url, {
            type: 'module',
        });

        this.workers = [];
        this.size = size;
    }


    submit() {
        
    }


    
}


// export default new ThreadEncWorker()