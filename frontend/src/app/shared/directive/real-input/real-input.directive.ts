import { Directive, ElementRef, EventEmitter, HostListener, Output, Input } from '@angular/core';

@Directive({
  selector: '[appRealInput]'
})
export class RealInputDirective {

  @Output() onValorAlterado: EventEmitter<any> = new EventEmitter<any>();
  @Input() valorInicial: number = 0;

  valor : string = '';
  valorInicialSetado : boolean = false;

  constructor(private el: ElementRef) { }

  ngOnChanges() {
    if ( this.valorInicialSetado === false && this.valorInicial > 0 ) {
      this.valor = (''+this.valorInicial);
      if ( this.valor.indexOf( '.' ) === -1 )
        this.valor += ".00";
      
      this.el.nativeElement.value = (''+this.valor).replace( '.', ',' );
      this.valorInicialSetado = true;
    }
  }

  @HostListener('keyup', ['$event']) 
  onKeyup( e : any ) {
    if( ( e.keyCode >= 48 && e.keyCode <= 57 ) || e.keyCode == 8 || e.keyCode == 16 )
      this.valor = e.target.value.replace( ',', '' );          

    this.el.nativeElement.value = this.valor;

    if ( this.valor.length > 2 ) {
      this.el.nativeElement.value = 
        this.valor.substring( 0, this.valor.length-2 ) + 
        ',' + 
        this.valor.substring( this.valor.length-2, this.valor.length );
    }

    let valorReal = 0;
    if ( this.el.nativeElement.value.length > 0 )
      valorReal = parseFloat( this.el.nativeElement.value.replace( ',', '.' ) );

    this.onValorAlterado.emit( { valorReal : valorReal } );
  }  

  setValor( valor : any ) {
    this.valor = ""+valor;

    if ( this.valor.indexOf( '.' ) === -1 ) {
      this.valor += ".00";
    }

    this.valor = this.valor.replace( '.', '' );          

    this.el.nativeElement.value = this.valor;

    if ( this.valor.length > 2 ) {
      this.el.nativeElement.value = 
        this.valor.substring( 0, this.valor.length-2 ) + 
        ',' + 
        this.valor.substring( this.valor.length-2, this.valor.length );
    }   
    
    let valorReal = 0;
    if ( this.el.nativeElement.value.length > 0 )
      valorReal = parseFloat( this.el.nativeElement.value.replace( ',', '.' ) );

    this.onValorAlterado.emit( { valorReal : valorReal } );
  }

}
