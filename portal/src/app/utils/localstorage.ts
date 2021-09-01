
export class LocalStorage {

    /**
     * Set the passed value in the localstorage using the passed key
     *
     * @param {string} key
     * @param {*} value
     * @memberof LocalStorage
     */
    public static setValue(key: string, value: any) {
        localStorage.setItem(key, JSON.stringify(value));
    }

    /**
     * Get the value from the localstorage for the passed key
     *
     * @param {string} key
     * @returns
     * @memberof LocalStorage
     */
    public static getValue(key: string) {
        const data: any = localStorage.getItem(key);
        return JSON.parse(data);
    }

    public static removeValue(key: string): void {
        localStorage.removeItem(key);
    }
}
