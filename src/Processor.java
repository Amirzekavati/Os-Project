record Processor(long id, long cache, long memory, long frequency) {
        Processor(long id, long cache, long memory, long frequency) {
        this.id = id;
        this.cache = cache;
        this.memory = memory;
        this.frequency = frequency;
        }

public long sumFeatures() {
        return this.frequency() + this.cache() + this.memory();
        }

public long id() {
        return this.id;
        }

public long cache() {
        return this.cache;
        }

public long memory() {
        return this.memory;
        }

public long frequency() {
        return this.frequency;
        }
        }
