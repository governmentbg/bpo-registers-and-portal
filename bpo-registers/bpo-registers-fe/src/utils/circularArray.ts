export class CircularArray<T> {
  private readonly elements: T[];
  private currentIndex: number;

  constructor(elements: T[]) {
    if (elements.length === 0) {
      throw new Error("Array must contain at least one element.");
    }
    this.elements = elements;
    this.currentIndex = 0;
  }

  getNext(): T {
    const element = this.elements[this.currentIndex];
    this.currentIndex = (this.currentIndex + 1) % this.elements.length;
    return element;
  }
}
