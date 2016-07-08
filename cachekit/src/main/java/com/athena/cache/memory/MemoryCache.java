package com.athena.cache.memory;

import com.athena.cache.Cache;
import com.athena.cache.CacheEntry;
import com.athena.cache.exception.CacheException;
import com.athena.cache.memory.journal.IMemoryJournal;
import com.athena.cache.memory.storage.IMemoryStorage;
import com.athena.toolkit.Assert;

import java.util.List;

/**
 * 内存缓存
 * @version 0.1 king 2016-04
 */
public final class MemoryCache extends Cache {

    private final IMemoryStorage mStorage;
    private final IMemoryJournal mJournal;

    public MemoryCache(IMemoryStorage storage,
                       IMemoryJournal journal,
                       long maxSize,
                       long maxQuantity) {
        super(maxSize, maxQuantity);
        this.mStorage = storage;
        this.mJournal = journal;
    }


    @Override
    protected <T> T doLoad(String key) throws CacheException {
        return (T) mStorage.load(key);
    }

    @Override
    protected <T> void doSave(String key, T value, int expires) throws CacheException {
        if (target == null || target == CacheTarget.NONE || target == CacheTarget.Disk) {
            return;
        }

        // 写入缓存
        mStorage.save(key, value);
        long createTime = System.currentTimeMillis();
        long expiresTime = createTime + expires;
        mJournal.put(key, new CacheEntry(key, createTime, expiresTime));
    }

    @Override
    protected boolean isExpiry(String key) {
        CacheEntry entry = mJournal.get(key);
        return entry == null || entry.isExpiry();
    }

    @Override
    public boolean containsKey(String key) {
        return mJournal.containsKey(key);
    }

    /**
     * 删除缓存
     * @param key
     */
    public void remove(String key) throws CacheException {
        mStorage.remove(key);
        mJournal.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clear() throws CacheException {
        mStorage.clear();
        mJournal.clear();
    }

    @Override
    public List<CacheEntry> snapshot() {
        return mJournal.snapshot();
    }

    @Override
    public String getLoseKey() throws CacheException {
        return mJournal.getLoseKey();
    }

    @Override
    public long getTotalSize() {
        long size = mStorage.getTotalSize();
        Assert.notLessThanZero(size);
        return size;
    }

    @Override
    public long getTotalQuantity() {
        long quantity = mStorage.getTotalQuantity();
        Assert.notLessThanZero(quantity);
        return quantity;
    }

}
