package hiperquiz.dao.impl;

import hiperquiz.dao.KeyGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class LongKeyGenerator implements KeyGenerator<Long> {
    private AtomicLong sequence;

    public LongKeyGenerator(long sequence) {
        this.sequence = new AtomicLong(sequence);
    }

    @Override
    public Long getNextId() {
        return sequence.incrementAndGet();
    }
}